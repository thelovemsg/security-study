import logMessage from "./logger.js";
class CalorieTracker{
    constructor(maxCaloires){
        this.maxCaloires = maxCaloires;
        this.currentCalories = 0
    }

    trackCalories(calorieCount){
        this.currentCalories += calorieCount
        if(this.currentCalories > this.maxCaloires){
            logMessage('Max caloires exceeded')
        }
    }

}

const calorieTracker = new CalorieTracker(2000)

calorieTracker.trackCalories(500)
calorieTracker.trackCalories(1000)
calorieTracker.trackCalories(700)