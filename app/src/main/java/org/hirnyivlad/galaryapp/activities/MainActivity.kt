package org.hirnyivlad.galaryapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import org.hirnyivlad.galaryapp.adapter.ImageAdapter
import org.hirnyivlad.galaryapp.api.ApiUtilities
import org.hirnyivlad.galaryapp.databinding.ActivityMainBinding
import org.hirnyivlad.galaryapp.model.Image
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private lateinit var mAdapter: ImageAdapter
    private lateinit var list: ArrayList<Image>
    private var page = 1
    private var pageSize = 30
    private var isLoading = true
    private var isLastPage = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        list = ArrayList()
        val manager = GridLayoutManager(this, 2)
        binding?.mainRecyclerView?.layoutManager = manager
        mAdapter = ImageAdapter(this, list)
        binding?.mainRecyclerView?.setHasFixedSize(true)
        binding?.mainRecyclerView?.adapter = mAdapter

        getData()
        binding?.mainRecyclerView?.addOnScrollListener(object : OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val totalItem: Int = manager.itemCount
                val firstVisibleItemPos = manager.findFirstVisibleItemPosition()
                if (!isLoading && !isLastPage) {
                    if ((manager.childCount + firstVisibleItemPos >= totalItem)
                        && firstVisibleItemPos >= 0
                        && totalItem >= pageSize
                    ) {
                        page++
                        getData()
                    }
                }

            }
        })
    }

    private fun getData() {
        isLoading = true
        isLastPage = false
        ApiUtilities.getApiInterface().getImages(page, 30)
            .enqueue(object : Callback<List<Image>> {
                override fun onResponse(
                    call: Call<List<Image>>,
                    response: Response<List<Image>>
                ) {
                    if (response.body() != null) {
                        list.addAll(response.body()!!)
                        mAdapter.notifyDataSetChanged()
                    }
                    isLoading = false

                    isLastPage = if (list.size > 0) {
                        list.size < pageSize
                    } else {
                        true
                    }
                }

                override fun onFailure(call: Call<List<Image>>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Error : + ${t.message}", Toast.LENGTH_LONG)
                        .show()
                }
            })
    }
}


