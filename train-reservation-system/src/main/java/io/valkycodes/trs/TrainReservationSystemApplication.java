package io.valkycodes.trs;

import io.valkycodes.trs.model.po.User;
import io.valkycodes.trs.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TrainReservationSystemApplication {

	@Autowired
	private AuthService authService;

	public static void main(String[] args) {
		SpringApplication.run(TrainReservationSystemApplication.class, args);
	}

}
