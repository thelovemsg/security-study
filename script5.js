class Store{
    constructor(user){
        this.PaymentProcessor = new PaymentProcessor(user)
    }

    purchaseBike(quantity){
        // this.stripe.makePayment(200 * quantity * 100)
        this.PaymentProcessor.makePayment(200 * quantity)
    }

    purchaseHelmet(quantity){
        // this.stripe.makePayment(15 * quantity * 100)
        this.PaymentProcessor.makePayment(15 * quantity)
    }
}

class StripePaymentProcessor {
    constructor(user) {
        this.user = user
        this.stripe = new Stripe(user)
    }

    pay(amountInDollars){
        this.stripe.makePayment(amountInDollars * 100)
    }
}

class PaypalPaymentProcessor {
    constructor(user) {
        this.user = user
        this.paypal = new Paypal()
    }

    pay(amountInDollars){
        this.paypal.makePayment(this.user, amountInDollars)
        this.stripe.makePayment(amountInDollars * 100)
    }
}

class Stripe {
    constructor(user) {
        this.user = user
    }
    makePayment(amountInDollars){
        console.log(`${this.user} made payment of $${amountInDollars} with Stripe`)
    }
}

class Paypal{
    makePayment(user,amountInDollars){
        console.log(`${user} made payment of $${amountInDollars} with Paypal`)
    }
}

const store = new Store(new StripePaymentProcessor('John'))
store.purchaseBike(2)
store.purchaseHelmet(2)