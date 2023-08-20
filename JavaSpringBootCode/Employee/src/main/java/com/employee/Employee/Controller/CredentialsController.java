package com.employee.Employee.Controller;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.Employee.Dao.BookingRepository;
import com.employee.Employee.Dao.EmployeeRepository;
import com.employee.Employee.Dao.ParkingLotRepository;
import com.employee.Employee.Model.AccountInfoDTO;
import com.employee.Employee.Model.BookingDetails;
import com.employee.Employee.Model.BookingInfoDTO;
import com.employee.Employee.Model.Credentials;
import com.employee.Employee.Model.CustomDateTimeFormattingExample;
import com.employee.Employee.Model.ParkingLot;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CredentialsController {
	@Autowired
	EmployeeRepository employeeRepo;
	@Autowired
	ParkingLotRepository parkingLotRepository;
	@Autowired
	BookingRepository bookingRepository; 
	
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody Credentials cd) {
		String message = "Registration successful";
		System.out.println("message"+cd.getConfirmPassword());
		System.out.println("message"+cd.getPassword());
		System.out.println("message"+cd.getUsername());

		try {
			if (employeeRepo.existsById(cd.getUsername())) {
				return new ResponseEntity<>("Username already exists.", HttpStatus.BAD_REQUEST);
			}

			employeeRepo
			.save(new Credentials(cd.getUsername(),cd.getPassword(),cd.getConfirmPassword()));
			return new ResponseEntity<>(message, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody Credentials cd) {
		String message = "login successful";
		System.out.println("message");
		 Optional<Credentials> user = employeeRepo.findById(cd.getUsername());
		 Credentials newCd= user.get();
	        if (newCd != null && newCd.getPassword().equals(cd.getPassword())) {
	        	return new ResponseEntity<>(message, HttpStatus.CREATED);// Authentication successful
	        }
	        return new ResponseEntity<>("Incorrect Username or Password.", HttpStatus.BAD_REQUEST);// Authentication failed

	}
	
	   @GetMapping("/parkingLot")
	    public ResponseEntity<List<ParkingLot>> getAllParkingLots() {
	        List<ParkingLot> parkingLots = parkingLotRepository.findAll();
	        for(ParkingLot park: parkingLots) {
	        	if(park.getAvailable_at()!=null)
	        	park.setAvailable_at_string(CustomDateTimeFormattingExample.formatDateTime(park.getAvailable_at()));
	        	if(park.getBooked_at()!=null)
	        	park.setBooked_at_string(CustomDateTimeFormattingExample.formatDateTime(park.getBooked_at()));
	        }
	        System.out.println(parkingLots);
	        return new ResponseEntity<>(parkingLots, HttpStatus.OK);
	    }
	   
	   
	   @PostMapping("/createBooking")
	    public ResponseEntity<List<ParkingLot>>createBooking(@RequestBody BookingDetails bookingDetails) {
	        // Assuming you have a Booking entity and BookingRepository
	        BookingDetails booking = new BookingDetails();
	        System.out.println("===========================bookingDetails"+bookingDetails);
	        booking.setCar_model(bookingDetails.getCar_model());
	        booking.setPlate_number(bookingDetails.getPlate_number());
	        booking.setBooking_duration(bookingDetails.getBooking_duration());
	        booking.setUsername(bookingDetails.getUsername());
	        booking.setParking_id(bookingDetails.getParking_id());

	        // Save the booking to the database
	        bookingRepository.save(booking);

	        
	        List<ParkingLot> parkingLots = parkingLotRepository.findAll();
	        for(ParkingLot park: parkingLots) {
	        	if(park.getAvailable_at()!=null)
	        	park.setAvailable_at_string(CustomDateTimeFormattingExample.formatDateTime(park.getAvailable_at()));
	        	if(park.getBooked_at()!=null)
	        	park.setBooked_at_string(CustomDateTimeFormattingExample.formatDateTime(park.getBooked_at()));
	        }
	        return new ResponseEntity<>(parkingLots, HttpStatus.OK);// Authentication successful
	    }
	   
		/*
		 * @PostMapping("/getBookingsForAccount") public ResponseEntity<AccountInfoDTO>
		 * getAccountInformation(@Param("username") String username) {
		 * Optional<Credentials> user = employeeRepo.findById(username); AccountInfoDTO
		 * accInfoDto= new AccountInfoDTO(); accInfoDto.setUsername(username);
		 * accInfoDto.setPassword(user.get().getPassword()); List<BookingInfoDTO>
		 * activeBookings = new ArrayList<BookingInfoDTO>(); for(Object ob:
		 * bookingRepository.findActiveBookingsByUsername(username)) { BookingInfoDTO
		 * booking = (BookingInfoDTO)ob; activeBookings.add(booking); }
		 * List<BookingInfoDTO> pastBookings = new ArrayList<BookingInfoDTO>();
		 * for(Object ob: bookingRepository.findPastBookingsByUsername(username)) {
		 * BookingInfoDTO booking = (BookingInfoDTO)ob; pastBookings.add(booking); }
		 * accInfoDto.setActiveBookings(activeBookings);
		 * accInfoDto.setPastBookings(pastBookings);
		 * System.out.println("accInfoDto"+accInfoDto); return new
		 * ResponseEntity<>(accInfoDto, HttpStatus.OK);// Authentication successful
		 * 
		 * }
		 */
	   
	   @PostMapping("/getBookingsForAccount")
	   public ResponseEntity<AccountInfoDTO> getAccountInformation(@RequestBody String username) {
	       Optional<Credentials> user = employeeRepo.findById(username);
	       AccountInfoDTO accInfoDto = new AccountInfoDTO();
	       accInfoDto.setUsername(username);
	       accInfoDto.setPassword(user.get().getPassword());

	       List<BookingInfoDTO> activeBookings = new ArrayList<>();
	       List<Object[]> activeBookingResults = bookingRepository.findActiveBookingsByUsername(username);
	       for (Object[] result : activeBookingResults) {
	           BookingInfoDTO booking = new BookingInfoDTO();
	           booking.setCarModel((String) result[0]);
	           booking.setPlateNumber((String) result[1]);
	           booking.setBookingDuration((Integer) result[2]);
	           booking.setBookedAt((LocalDateTime) result[3]);
	           booking.setAvailableAt((LocalDateTime) result[4]);
	           activeBookings.add(booking);
	       }

	       List<BookingInfoDTO> pastBookings = new ArrayList<>();
	       List<Object[]> pastBookingResults = bookingRepository.findPastBookingsByUsername(username);
	       for (Object[] result : pastBookingResults) {
	           BookingInfoDTO booking = new BookingInfoDTO();
	           booking.setCarModel((String) result[0]);
	           booking.setPlateNumber((String) result[1]);
	           booking.setBookingDuration((Integer) result[2]);
	           booking.setBookedAt((LocalDateTime) result[3]);
	           booking.setAvailableAt((LocalDateTime) result[4]);
	           pastBookings.add(booking);
	       }

	       accInfoDto.setActiveBookings(activeBookings);
	       accInfoDto.setPastBookings(pastBookings);

	       return new ResponseEntity<>(accInfoDto, HttpStatus.OK);
	   }
}