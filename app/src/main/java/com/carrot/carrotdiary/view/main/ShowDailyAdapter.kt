package com.carrot.carrotdiary.view.main

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.carrot.carrotdiary.databinding.ItemDailyBinding
import com.carrot.carrotdiary.model.Daily
import com.carrot.carrotdiary.view.main.diary.DiaryDetailActivity


// 나중에 페이징 어뎁터로 리펙토링
class ShowDailyAdapter(private val context: Context) :
    ListAdapter<Daily, ShowDailyAdapter.DailyViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Daily>() {
            override fun areItemsTheSame(oldItem: Daily, newItem: Daily): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: Daily,
                newItem: Daily
            ): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return DailyViewHolder(
            ItemDailyBinding.inflate(
                inflater,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DailyViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item, context)
        }
    }

    class DailyViewHolder(private val itemDailyBinding: ItemDailyBinding) :
        RecyclerView.ViewHolder(itemDailyBinding.root) {
        fun bind(item: Daily, context: Context) {
            // itemDailyBinding.commentIvDaily.t = item.id.toString()
            itemDailyBinding.itemDaily.setOnClickListener {
                val intent = Intent(context, DiaryDetailActivity::class.java)
                itemDailyBinding.root.context.startActivity(intent)
                Toast.makeText(context, "${item.id.toString()} 번 아이템 클릭", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
