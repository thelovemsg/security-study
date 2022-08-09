interface Entity {
    attackDamage
    health
    name

    move()
    attack()
    takeDamage(amount)
}

class Character implements Entity{
    move() {

    }

    attack() {

    }
    
    takeDamage() {

    }
}

class Turret implements Entity{
    move() {

    }
}