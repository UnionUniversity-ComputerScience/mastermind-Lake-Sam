package mastermind

data class Evaluation(val rightPosition: Int, val wrongPosition: Int)



fun evaluateGuess(guess: String, secret: String): Evaluation {
    val codeUsed = BooleanArray(secret.length)      //using a boolean array so if we find a correct pin we don't check it twice
    val guessUsed = BooleanArray(guess.length)



    var correct = 0
    var match = 0

    // Compare correct color and position to determine if they are the correct color and correct position
    for (i in 0 until secret.length) {  //looping through secret
        if (secret.get(i) === guess[i]) {  //comparing secret and guess for exact matches
            correct++                           //adding to the number of correct pins
            guessUsed[i] = true                 //setting values in our boolean array
            codeUsed[i] = guessUsed[i]
        }
    }

    // Compare matching colors for "pins" of the right color but wrong position
    for (i in secret.indices) {                 //A nested loop so we can compare for right color wrong position
        for (j in guess.indices) {
            if (!codeUsed[i] && !guessUsed[j] && secret.get(i) === guess[j]) {
                match++
                guessUsed[j] = true
                codeUsed[i] = guessUsed[j]
                break
            }
        }
    }

    return Evaluation( correct, match)
}