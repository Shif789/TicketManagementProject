package in.ineuron.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ineuron.dao.ITouristRepo;
import in.ineuron.exception.TouristNotFoundException;
import in.ineuron.model.Tourist;

@Service
public class TouristMgmtServiceImpl implements ITouristMgmtService {

	@Autowired
	private ITouristRepo repo;

	@Override
	public String registerTourist(Tourist tourist) {
		Tourist savedTourist = null;
		savedTourist = repo.save(tourist);
		System.out.println(savedTourist);
		return savedTourist != null ? "Tourist is registered with the id: " + savedTourist.getTid()
				: "Tourist registration failed";
	}

	@Override
	public List<Tourist> fetchAllTourist() {
		List<Tourist> list = repo.findAll();
		list.sort((t1, t2) -> t1.getTid().compareTo(t2.getTid()));
		return list;
	}

	@Override
	public Tourist fetchTouristById(Integer id) {
//		Optional<Tourist> optional = repo.findById(id);
//		if (optional.isPresent())
//			return optional.get();
//		else
//			throw new TouristNotFoundException("Tourist not found with id: " + id);

		return repo.findById(id).orElseThrow(() -> new TouristNotFoundException("Tourist not found with id: " + id));
	}

	@Override
	public String updateTourist(Tourist tourist) {
		Optional<Tourist> optional = repo.findById(tourist.getTid());
		if (optional.isPresent()) {
			Tourist savedTourist = repo.save(tourist);
			return "Tourist is updated with id: " + savedTourist.getTid();
		} else
			throw new TouristNotFoundException("Tourist not found with id: " + tourist.getTid());

	}

	@Override
	public String updateTouristBudgetById(Integer id, Float hikePercent) {
		Optional<Tourist> optional = repo.findById(id);
		if (optional.isPresent()) {
			Tourist tourist = optional.get();
			tourist.setBudget(tourist.getBudget() + (tourist.getBudget() * (hikePercent / 100)));
			repo.save(tourist);
			return "Tourist is updated with id: " + id;
		} else
			throw new TouristNotFoundException("Tourist not found with id: " + id);

	}

	@Override
	public String deleteTouristById(Integer id) {
		Optional<Tourist> optional = repo.findById(id);
		if (optional.isPresent()) {
			//repo.deleteById(id);
			repo.delete(optional.get());
			return "Tourist deleted with id: " + id;
		} else
			throw new TouristNotFoundException("Tourist with id: " + id+" not found for deletion");
	
	}

}
