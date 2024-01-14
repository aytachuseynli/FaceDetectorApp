package com.aytachuseynli.facedetectorapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aytachuseynli.facedetectorapp.databinding.ItemResultBinding
import com.aytachuseynli.facedetectorapp.utils.GenericDiffUtil
import com.aytachuseynli.facedetectorapp.data.model.Result


class TestResultAdapter: ListAdapter<Result, TestResultAdapter.ViewHolder>(GenericDiffUtil<Result>(
    myItemsTheSame = { oldItem, newItem -> oldItem.id == newItem.id },
    myContentsTheSame = { oldItem, newItem -> oldItem == newItem }
)) {


    inner class ViewHolder(private val binding: ItemResultBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Result) {
            with(binding) {


            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}