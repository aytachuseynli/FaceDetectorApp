package com.aytachuseynli.facedetectorapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aytachuseynli.facedetectorapp.data.model.FaceDetectionResult
import com.aytachuseynli.facedetectorapp.databinding.ItemResultBinding
import com.aytachuseynli.facedetectorapp.utils.GenericDiffUtil
import java.text.DateFormat
import java.util.Date


class TestResultAdapter: ListAdapter<FaceDetectionResult, TestResultAdapter.ViewHolder>(GenericDiffUtil<FaceDetectionResult>(
    myItemsTheSame = { oldItem, newItem -> oldItem.id == newItem.id },
    myContentsTheSame = { oldItem, newItem -> oldItem == newItem }
)) {


    inner class ViewHolder(private val binding: ItemResultBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: FaceDetectionResult) {
            with(binding) {
                val formattedDate =
                    DateFormat.getDateTimeInstance().format(Date(item.testFinishTime ?: 0))

                dateTxt.text = "Test Date: $formattedDate"
                successTxt.text = "Success Rate: ${calculateSuccessRate(item)}%"

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

    private fun calculateSuccessRate(result: FaceDetectionResult): Int {
        var successCount = 0
        if (result.left == true) successCount++
        if (result.right == true) successCount++
        if (result.smile == true) successCount++
        if (result.neutral == true) successCount++

        return (successCount.toDouble() / 4 * 100).toInt()
    }

}