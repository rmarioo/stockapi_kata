package stockapi

import arrow.data.fix
import arrow.data.run
import org.hamcrest.core.Is.`is`
import org.junit.Assert.assertThat
import org.junit.Test

class StockApiTest {

    @Test
    fun `sell stocks monad`() {

        val stockApi: StockApi = InMemoryStockApi(mapOf("GE" to 10, "FIAT" to 2, "LMN" to 20))

        val program = stockApi.sell(  stockName = "GE", quantity  = 10)

        val (portfolioResult, amount) = program.fix().run(Portfolio(mapOf("GE" to 50, "FIAT" to 100)))

        assertThat(portfolioResult, `is`(Portfolio(mapOf("GE" to 40, "FIAT" to 100))))
        assertThat(amount, `is`(10*10))
    }

    @Test
    fun `buy stocks monad`() {

        val stockApi: StockApi = InMemoryStockApi(mapOf("GE" to 10, "FIAT" to 2, "LMN" to 20))

        val program = stockApi.buy(stockName  = "GE", amount = 50)

        val (portfolioResult, amount) = program.fix().run(Portfolio(mapOf("GE" to 50, "FIAT" to 100)))

        assertThat(portfolioResult, `is`(Portfolio(mapOf("GE" to 50+(50/10), "FIAT"  to 100))))
        assertThat(amount, `is`(50/10))
    }

    @Test
    fun `transfer stocks monad`() {


        val stockApi: StockApi = InMemoryStockApi(mapOf("GE" to 10, "FIAT" to 2, "LMN" to 20))

        val program = stockApi.transfer(fromName  = "GE",toName    = "FIAT")

        val (portfolioResult, amount) = program.fix().run(Portfolio(mapOf("GE" to 50, "FIAT" to 100)))

        assertThat(portfolioResult, `is`(Portfolio(mapOf("GE" to 0, "FIAT" to 100 + 50 *10 /2 ))))
        assertThat(amount, `is`(250))
    }
}