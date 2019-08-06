package `in`.ponshere.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        GlobalScope.launch(Dispatchers.Main) {
            val value = getValueFromNetwork()
            Toast.makeText(this@MainActivity, value, Toast.LENGTH_SHORT).show()
        }

    }


    private suspend fun getValueFromNetwork(): String {
        return GlobalScope.async(Dispatchers.IO) {
            var counter = 0
            while (true) {
                Log.d("Coroutine", "Incrementing the counter ${++counter}")
                delay(1000)
                if (counter == 10) break
            }

            return@async "test"
        }.await()
    }
}
