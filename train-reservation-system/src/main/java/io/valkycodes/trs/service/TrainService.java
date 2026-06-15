package io.valkycodes.trs.service;

import io.valkycodes.trs.model.po.Train;
import io.valkycodes.trs.repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainService {

    @Autowired
    private TrainRepository trainRepository;

    public List<Train> getAllTrain() {
        return trainRepository.findAll();
    }

    public Optional<Train> getTrainByNumber(Integer trainNumber) {
        return trainRepository.findById(trainNumber);
    }

    public List<Train> findTrainsByRoute(String source, String destination) {
        return trainRepository.findBySourceIgnoreCaseAndDestinationIgnoreCase(source, destination);
    }

    public boolean isSeatAvailable(Integer trainNumber) {
        Optional<Train> optionalTrain = trainRepository.findById(trainNumber);

        if (optionalTrain.isPresent()) {
            Train train = optionalTrain.get();
            return train.getSeatAvailable() > 0;
        }

        return false;
    }

    public boolean deductSeats(Integer trainNumber, int seats) {

        if (seats <= 0)
            return false;

        Optional<Train> optionalTrain = trainRepository.findById(trainNumber);

        if (optionalTrain.isPresent()) {
            Train train = optionalTrain.get();
            if (seats <= train.getSeatAvailable()) {
                train.setSeatAvailable(train.getSeatAvailable() - seats);
                trainRepository.save(train);
                return true;
            }
        }

        return false;
    }

    public boolean releaseSeats(Integer trainNumber, int seats) {

        if (seats <= 0)
            return false;

        Optional<Train> optionalTrain = trainRepository.findById(trainNumber);

        if (optionalTrain.isPresent()) {
            Train train = optionalTrain.get();
            if (seats <= train.getSeatAvailable()) {
                train.setSeatAvailable(train.getSeatAvailable() + seats);
                trainRepository.save(train);
                return true;
            }
        }

        return false;
    }

}
