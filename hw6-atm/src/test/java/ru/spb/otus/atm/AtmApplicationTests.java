package ru.spb.otus.atm;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Написать эмулятор АТМ

 Объект класса АТМ должен уметь
 • принимать банкноты разных номиналов (на каждый номинал должна быть своя ячейка)
 • выдавать запрошенную сумму минимальным количеством банкнот или ошибку если сумму нельзя выдать
 • выдавать сумму остатка денежных средств
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class AtmApplicationTests {

	@Test
	public void contextLoads() {
	}

}
