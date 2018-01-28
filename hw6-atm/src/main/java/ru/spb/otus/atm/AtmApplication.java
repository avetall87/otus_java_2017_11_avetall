package ru.spb.otus.atm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.spb.otus.atm.service.BanknoteAtm;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class AtmApplication {

	private static BanknoteAtm banknoteAtm;

	@Autowired
	public AtmApplication(BanknoteAtm banknoteAtm) {
		AtmApplication.banknoteAtm = banknoteAtm;
	}

	public static void main(String[] args) {
		SpringApplication.run(AtmApplication.class, args);

		List<Integer> dataSet = new ArrayList<>();
		dataSet.add(10);
		dataSet.add(20);
		dataSet.add(30);
		dataSet.add(50);
		dataSet.add(75);
		dataSet.add(100);
		dataSet.add(100);
		dataSet.add(100);
		dataSet.add(100);
		dataSet.add(100);
		dataSet.add(1000);
		dataSet.add(1000);
		dataSet.add(5000);
		dataSet.add(5000);
		dataSet.add(5000);

		List<Integer> receive = banknoteAtm.receive(dataSet);

		System.out.println("Wrong Banknote type: "+receive);
		System.out.println("Current balance: " + banknoteAtm.getBalance());
		System.out.println("Sum: "+ banknoteAtm.getSum(487));
	}
}
