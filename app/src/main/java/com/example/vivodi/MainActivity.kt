package com.example.vivodi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.example.vivodi.databinding.ActivityMainBinding
import com.example.vivodi.databinding.AddProductActivityBinding
import com.example.vivodi.type.Product
import com.example.vivodi.type.User
import com.example.vivodi.type.products.Book
import java.math.BigDecimal
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private var printUi:Boolean = false
    private fun stringPrint(string:String){
        if(printUi)
            binding.TutText.setText(string)
        else {
            binding.TutText.setText("")
            Log.d("test", string)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.RadioGroup.setOnCheckedChangeListener{_, id ->
            printUi = id == R.id.TextVivod
        }
        binding.Array.setOnClickListener{
            val list=mutableListOf<Int>()
            for(i in 0 until (3..5).random()){
                list.add(Random.nextInt(0,100))
            }
            stringPrint(list.joinToString())
        }
        binding.LPU.setOnClickListener {
            val user = User()
            user.login="Log123"
            user.password="Pas123123"
            stringPrint(user.login+" - "+user.password)
            }
        binding.LPA.setOnClickListener {
            val user = User()
            user.login="Admin"
            user.password="AdminAdmin"
            stringPrint(user.getInfo())
        }
        binding.PP.setOnClickListener {
            val product = Product("Молоко", BigDecimal.valueOf(69.99))
            stringPrint(product.getInfo())
        }
        binding.BPE.setOnClickListener {
            val book = Book("Какая-то", BigDecimal.valueOf(228.69), 1337)
            stringPrint(book.getInfo())
        }
        val ProductList = mutableListOf<Product>()
        ProductList.add(Product("Что-то 1", BigDecimal.valueOf(1337.99)))
        ProductList.add(Product("Что-то 2", BigDecimal.valueOf(777.77)))
        ProductList.add(Book("Что-то 3", BigDecimal.valueOf(69.00), 1))
        binding.PList.setOnClickListener {
            stringPrint(ProductList.joinToString("\n"){p -> p.getInfo()})
        }
        binding.PA.setOnClickListener {
            val dialog = AlertDialog.Builder(this)
            dialog.setTitle("Добавление продукта")
            val dialogAdd = AddProductActivityBinding.inflate(layoutInflater)
            dialog.setView(dialogAdd.root)
            dialogAdd.BookPage.visibility= View.GONE
            dialogAdd.CheckIsBook.setOnCheckedChangeListener{_, b ->
                dialogAdd.BookPage.visibility = if(b) View.VISIBLE else View.GONE
            }
            dialog.setPositiveButton("Добавить"){
                _, _ ->
                if(dialogAdd.CheckIsBook.isChecked){
                    ProductList.add(Book(
                        dialogAdd.ProductName.text.toString(),
                        dialogAdd.ProductPrice.text.toString().toBigDecimal(),
                        dialogAdd.BookPage.text.toString().toInt()
                    ))
                }else{
                    ProductList.add(
                        Product(
                            dialogAdd.ProductName.text.toString(),
                            dialogAdd.ProductPrice.text.toString().toBigDecimal()
                        )
                    )
                }

            }
            dialog.show()
        }
        binding.btnProudctList.setOnClickListener {
            val ActivityProduct = Intent(this, ProductListActivity::class.java)
            startActivity(ActivityProduct)
        }
        }
    }



