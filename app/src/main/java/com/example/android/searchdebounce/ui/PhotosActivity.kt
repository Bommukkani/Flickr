package com.example.android.searchdebounce.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.android.searchdebounce.R
import kotlinx.android.synthetic.main.activity_photos.*

class PhotosActivity : AppCompatActivity() {
    private val photosViewModel: PhotosViewModel by viewModels()

    private val photosAdapter = PhotosAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photos)

        photosRecyclerView.adapter = photosAdapter
        photosRecyclerView.layoutManager = GridLayoutManager(this, 3)

        getData("")

        etSearchFlickr.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                getData(s.toString())
            }
        })
    }


    fun getData(search: String) {
        photosViewModel.photosLiveData.observe(this,
            Observer { list ->
                with(photosAdapter) {
                    photos.clear()
                    if (search.equals(""))
                        photos.addAll(list)
                    else {
                        val b = list.filter { it.title.contains(search) }
                        photos.addAll(b)

                    }
                    notifyDataSetChanged()
                }
            })
    }
}
