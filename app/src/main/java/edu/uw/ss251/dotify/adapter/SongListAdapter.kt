package edu.uw.ss251.dotify.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ericchee.songdataprovider.Song
import edu.uw.ss251.dotify.databinding.ItemSongBinding

class SongListAdapter(private var songs: List<Song>): RecyclerView.Adapter<SongListAdapter.SongItemHolder>() {

    var onSongClickListener: (song: Song) -> Unit = {_ -> }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongItemHolder {
        val binding = ItemSongBinding.inflate(LayoutInflater.from(parent.context))
        return SongItemHolder(binding)
    }

    override fun getItemCount(): Int = songs.size

    override fun onBindViewHolder(holder: SongItemHolder, position: Int) {
        val song = songs[position]

        with(holder.binding) {
            itemSong.setOnClickListener{
                onSongClickListener(song)
            }
            tvSongTitle.text = song.title
            tvSongArtist.text = song.artist
            ivSongPic.setImageResource(song.smallImageID)
        }
    }

    fun updateSongs(newList: List<Song>) {
        this.songs = newList
        notifyDataSetChanged()
    }


    class SongItemHolder(val binding: ItemSongBinding): RecyclerView.ViewHolder(binding.root)
}