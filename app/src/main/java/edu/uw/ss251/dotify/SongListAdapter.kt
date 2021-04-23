package edu.uw.ss251.dotify

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ericchee.songdataprovider.Song
import edu.uw.ss251.dotify.databinding.ItemSongBinding

class SongListAdapter(private val songs: List<Song>): RecyclerView.Adapter<SongListAdapter.SongItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongItemHolder {
        val binding = ItemSongBinding.inflate(LayoutInflater.from(parent.context))
        return SongItemHolder(binding)
    }

    override fun getItemCount(): Int = songs.size

    override fun onBindViewHolder(holder: SongItemHolder, position: Int) {
        val song = songs[position]

        with(holder.binding) {
            tvSongTitle.text = song.title
            tvSongArtist.text = song.artist
            ivSongPic.setImageResource(song.smallImageID)
        }
    }


    class SongItemHolder(val binding: ItemSongBinding): RecyclerView.ViewHolder(binding.root)
}