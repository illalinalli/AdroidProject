package ru.alinas.cats.ui.favorites

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import ru.alinas.cats.R
import ru.alinas.cats.db.CatEntity

class CatAdapter(private val catList: List<CatEntity>) :
    RecyclerView.Adapter<CatAdapter.CatViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cat, parent, false)
        return CatViewHolder(view)
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        val cat = catList[position]
        holder.bind(cat)
    }

    override fun getItemCount(): Int {
        return catList.size
    }

    inner class CatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.rootView as ImageView

        fun bind(cat: CatEntity) {
            val bitmap = BitmapFactory.decodeByteArray(cat.img, 0, cat.img.size)
            imageView.setImageBitmap(bitmap)
        }
    }
}
