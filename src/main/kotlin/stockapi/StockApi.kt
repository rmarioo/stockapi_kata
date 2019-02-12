package stockapi

data class Portfolio(val map: Map<String, Int>)

data class TransactionResult(val amount: Int, val portfolio: Portfolio)

typealias Transaction = (Portfolio) -> TransactionResult

interface StockApi
{
    fun get(stockName: String): Transaction

    fun sell(stockName: String, quantity: Int): Transaction

    fun buy(stockName: String, amount: Int): Transaction

    fun transfer(fromName: String, toName: String): Transaction =

            get(fromName)
                    .flatMap { sell(fromName, it) }
                    .flatMap { buy(toName, it) }



    infix fun Transaction.flatMap(f: (Int) -> (Transaction)): Transaction = { portfolio ->
        val (amount, newPortfolio) = this(portfolio)
        f(amount)(newPortfolio)
    }

}


