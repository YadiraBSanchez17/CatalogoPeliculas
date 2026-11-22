package com.example.catalogopeliculas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MovieAdapter(
    private var movies: List<Movie>,
    private val onMovieClick: (Movie) -> Unit
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgPoster: ImageView = view.findViewById(R.id.imgPoster)
        val txtTitulo: TextView = view.findViewById(R.id.txtTituloCard)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.txtTitulo.text = movie.titulo

        Glide.with(holder.itemView.context)
            .load(movie.imagen)
            .into(holder.imgPoster)

        holder.itemView.setOnClickListener { onMovieClick(movie) }
    }

    override fun getItemCount(): Int = movies.size

    fun updateData(newMovies: List<Movie>) {
        movies = newMovies
        notifyDataSetChanged()
    }
}