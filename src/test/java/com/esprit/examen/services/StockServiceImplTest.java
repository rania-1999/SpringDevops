package com.esprit.examen.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.esprit.examen.entities.Stock;

@RunWith(SpringRunner.class)
@SpringBootTest
class StockServiceImplTest {

	@Autowired
	IStockService stockService;

	@Test
	void testAddStock() {
		// List<Stock> stocks = stockService.retrieveAllStocks();
		// int expected=stocks.size();
		Stock s = new Stock("stock test", 10, 100);
		Stock savedStock = stockService.addStock(s);

		// assertEquals(expected+1, stockService.retrieveAllStocks().size());
		assertNotNull(savedStock.getLibelleStock());
		stockService.deleteStock(savedStock.getIdStock());

	}

	@Test
	void testAddStockOptimized() {

		Stock s = new Stock("stock test", 10, 100);
		Stock savedStock = stockService.addStock(s);
		assertNotNull(savedStock.getIdStock());
		assertSame(10, savedStock.getQte());
		assertTrue(savedStock.getQteMin() > 0);
		stockService.deleteStock(savedStock.getIdStock());

	}

	@Test
	void testDeleteStock() {
		Stock s = new Stock("stock test", 30, 60);
		Stock savedStock = stockService.addStock(s);
		stockService.deleteStock(savedStock.getIdStock());
		assertNull(stockService.retrieveStock(savedStock.getIdStock()));
	}

}
