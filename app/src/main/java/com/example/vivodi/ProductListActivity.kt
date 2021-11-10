package com.example.vivodi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vivodi.adapters.ProductsAdapter
import com.example.vivodi.databinding.ActivityProductListBinding
import com.example.vivodi.databinding.AddProductActivityBinding
import com.example.vivodi.type.Product
import com.example.vivodi.type.products.Book
import java.math.BigDecimal

class ProductListActivity : AppCompatActivity() {
    private lateinit var binding:ActivityProductListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityProductListBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val productList = mutableListOf<Product>()
        productList.add(Product("Апельсин", BigDecimal.valueOf(36.21)))
        productList.add(Product("Чайник", BigDecimal.valueOf(350.00)))
        productList.add(Book("Черный Джо", BigDecimal.valueOf(179.99), 3))
        binding.RecyclerProducts.layoutManager=LinearLayoutManager(this)
        binding.RecyclerProducts.adapter = ProductsAdapter(productList)
        binding.btnAddProduct.setOnClickListener {
            val dialog = AlertDialog.Builder(this)
            dialog.setTitle("Добавление продукта")
            val dialogADD = AddProductActivityBinding.inflate(layoutInflater)
            dialog.setView(dialogADD.root)
            dialogADD.BookPage.visibility = View.GONE
            dialogADD.CheckIsBook.setOnCheckedChangeListener{ _, b ->
                dialogADD.BookPage.visibility = if(b) View.VISIBLE else View.GONE
            }
            dialog.setPositiveButton("Добавить"){ _, _ ->
                if(dialogADD.CheckIsBook.isChecked){
                    productList.add(Book(
                            dialogADD.ProductName.text.toString(),
                            dialogADD.ProductPrice.text.toString().toBigDecimal(),
                            dialogADD.BookPage.text.toString().toInt()
                        )
                    )
                }else{
                    productList.add(
                        Product(
                            dialogADD.ProductName.text.toString(),
                            dialogADD.ProductPrice.text.toString().toBigDecimal(),
                        )
                    )
                }
                binding.RecyclerProducts.adapter?.notifyItemInserted(productList.lastIndex)
            }
            dialog.show()
        }

    }
}