package com.example.pokemon.data.localData

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [PokemonRequestLocal::class], version = 2, exportSchema = false)
abstract class PokemonDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao

    companion object {
        @Volatile
        private var INSTANCE: PokemonDatabase? = null

        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // SQLite ALTER TABLE syntax to add the 'type' column
                database.execSQL("ALTER TABLE pokemon_table ADD COLUMN type TEXT NOT NULL DEFAULT ''")
            }
        }

        fun getInstance(context: Context): PokemonDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PokemonDatabase::class.java,
                    "pokemon_database"
                )
                    .addMigrations(MIGRATION_1_2)  // Add your migration here
                    .fallbackToDestructiveMigration()  // Optional: Allow destructive migration
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}