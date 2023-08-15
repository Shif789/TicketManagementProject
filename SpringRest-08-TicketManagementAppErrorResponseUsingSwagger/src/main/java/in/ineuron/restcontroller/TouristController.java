package in.ineuron.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.ineuron.model.Tourist;
import in.ineuron.service.ITouristMgmtService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/tourist")
public class TouristController {

	@Autowired
	private ITouristMgmtService touristMgmtService;

	@PostMapping(value = "/register")
	@ApiOperation("For Tourist registration")
	public ResponseEntity<String> enrollTourist(@RequestBody Tourist tourist) {

		String body = touristMgmtService.registerTourist(tourist);
		return new ResponseEntity<>(body, HttpStatus.OK);

	}

	@GetMapping("/findAll")
	@ApiOperation("To Display All The Tourist Records")
	public ResponseEntity<?> displayTouristInfo() {

		List<Tourist> allTourist = touristMgmtService.fetchAllTourist();
		return new ResponseEntity<>(allTourist, HttpStatus.OK);

	}

	@GetMapping("/findById/{id}")
	@ApiOperation("To Display Tourist Record Selected By Id")
	public ResponseEntity<?> displayTouristById(@PathVariable Integer id) {

		Tourist touristById = touristMgmtService.fetchTouristById(id);
		return new ResponseEntity<>(touristById, HttpStatus.OK);

	}

	@PutMapping("/modify")
	@ApiOperation("To Update Tourist Record By Providing all information")
	public ResponseEntity<String> modifyTourist(@RequestBody Tourist tourist) {

		String updateStatus = touristMgmtService.updateTourist(tourist);
		return new ResponseEntity<>(updateStatus, HttpStatus.OK);

	}

	@PatchMapping("/budgetModify/{id}/{hike}")
	@ApiOperation("To Update Tourist Budget Selected By Id")
	public ResponseEntity<String> modifyTouristBudgetById(@PathVariable Integer id,
			@PathVariable("hike") Float hikeAmt) {

		String updateStatus = touristMgmtService.updateTouristBudgetById(id, hikeAmt);
		return new ResponseEntity<>(updateStatus, HttpStatus.OK);

	}

	@DeleteMapping("/delete/{id}")
	@ApiOperation("To Delete Tourist Record Selected By Id")
	public ResponseEntity<String> removeTouristById(@PathVariable Integer id) {

		String deleteStatus = touristMgmtService.deleteTouristById(id);
		return new ResponseEntity<>(deleteStatus, HttpStatus.OK);

	}

}
