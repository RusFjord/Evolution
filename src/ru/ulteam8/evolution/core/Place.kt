package ru.ulteam8.evolution.core

class Place private constructor(){
     companion object {
         fun create() : Place {
             return Place()
         }
     }
}
