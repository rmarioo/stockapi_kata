package stockapi

import org.hamcrest.core.Is.`is`
import org.junit.Assert.assertThat
import org.junit.Test

class StockApiTest {


    @Test
    fun `sell stocks v1`() {

        val stockApi: StockApi = InMemoryStockApi(mapOf("GE" to 10, "FIAT" to 2, "LMN" to 20))

        val transactionResult = stockApi.sell(  stockName = "GE",
                                                quantity  = 10,
                                                portfolio = Portfolio(mapOf("GE" to 50, "FIAT" to 100)))

        assertThat(transactionResult.portfolio, `is`(Portfolio(mapOf("GE" to 40, "FIAT" to 100))))
        assertThat(transactionResult.amount, `is`(10*10))
    }


    @Test
    fun `buy stocks v1`() {

        val stockApi: StockApi = InMemoryStockApi(mapOf("GE" to 10, "FIAT" to 2, "LMN" to 20))

        val transactionResult = stockApi.buy(
                stockName  = "GE",
                amount = 50,
                portfolio = Portfolio(mapOf("GE" to 50, "FIAT" to 100)))

        assertThat(transactionResult.portfolio, `is`(Portfolio(mapOf("GE" to 50+(50/10), "FIAT"  to 100))))
        assertThat(transactionResult.amount, `is`(50/10))
    }


  @Test
    fun `transfer stocks v1`() {


        val stockApi: StockApi = InMemoryStockApi(mapOf("GE" to 10, "FIAT" to 2, "LMN" to 20))

        val transactionResult = stockApi.transfer(
                fromName  = "GE",
                toName    = "FIAT",
                portfolio = Portfolio(mapOf("GE" to 50,"FIAT" to 100)))

        assertThat(transactionResult.portfolio, `is`(Portfolio(mapOf("GE" to 0, "FIAT" to 100 + 50 *10 /2 ))))
        assertThat(transactionResult.amount, `is`(250))
    }
}