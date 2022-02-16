package com.hangman

import java.io.File
import java.util.*
import kotlin.collections.ArrayList

fun main() {

    val scanner = Scanner(File("C:/Users/erick/Desktop/palavras.txt"))
    val wordsList = ArrayList<String>()
    val playerGuesses = ArrayList<Char>()
    var word: String

    println("1 ou 2 jogadores?")
    val players = readLine()

    if(players.equals("1")) {
        while(scanner.hasNext()) {
            wordsList.add(scanner.nextLine())
        }
        val random = Random()
        word = wordsList[random.nextInt(wordsList.size)]
        skipLines()
    }else {
        println("\nJogador 1, informe a palavra do jogo para o Jogador 2: ")
        word = readLine().toString()
        skipLines()
        println("Jogador 2, agora é sua hora de adivinhar a palavra!\n")
    }

    var wrongCount = 0
    while(true) {
        printHangedMan(wrongCount)
        if(wrongCount >= 6) {
            println("Você está MORTO!")
            println("A palavra era: $word")
            break
        }
        printGameState(word, playerGuesses)
        if(!getPlayerInput(word, playerGuesses)) wrongCount++
        if (printGameState(word, playerGuesses)) break
        println("\nInforme o seu palpite para a palavra: ")
        when {
            readLine().equals(word) -> {
                println("\nVocê venceu! Parabéns!")
                break
            }
            else -> println("Palpite errado. Tente novamente!\n")
        }
    }
}

fun printGameState(word: String, playerGuesses: List<Char>): Boolean {
    var count = 0
    for (index: Int in word.indices) when {
        playerGuesses.contains(word[index]) -> {
            count++
            print(word[index])
        }
        else -> print("-")
    }
    return word.length == count
}

fun printHangedMan(wrongCount: Int) {
    println(" -------")
    println(" |     |")
    if (wrongCount >= 1) println(" O")
    if (wrongCount >= 2) {
        print("\\ ")
        if (wrongCount >= 3) println("/")
        else println("")
    }
    if (wrongCount >= 4) println(" |")
    if (wrongCount >= 5) {
        print("/ ")
        if (wrongCount >= 6) println("\\")
        else println("")
    }
    println("")
    println("")
}

fun getPlayerInput(word: String, playerGuesses: ArrayList<Char>): Boolean {
    println("\nEscolha uma letra: ")
    val letter = readLine()
    println("")
    return when {
        letter != null -> {
            playerGuesses.add(letter[0])
            word.contains(letter)
        }
        else -> false
    }
}

fun skipLines() {
    println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n")
    println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n")
}