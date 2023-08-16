package com.example.artbook.di

import android.content.Context
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.artbook.R
import com.example.artbook.data.repository.ArtRepositoryImpl
import com.example.artbook.data.service.ImageApiService
import com.example.artbook.data.source.local.ArtDao
import com.example.artbook.data.source.local.ArtDatabase
import com.example.artbook.domain.repository.ArtRepository
import com.example.artbook.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun injectRoomDb(
        @ApplicationContext context: Context,
    ) = Room.databaseBuilder(
        context,
        ArtDatabase::class.java,
        "ArtBookDb",
    ).build()

    @Singleton
    @Provides
    fun injectDao(
        database: ArtDatabase,
    ) = database.artDao()

    @Singleton
    @Provides
    fun injectAPI(): ImageApiService {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ImageApiService::class.java)
    }

    @Singleton
    @Provides
    fun injectNormalRepo(dao: ArtDao, api: ImageApiService) = ArtRepositoryImpl(dao, api) as ArtRepository

    @Singleton
    @Provides
    fun injectGlide(@ApplicationContext context: Context) = Glide.with(context)
        .setDefaultRequestOptions(
            RequestOptions().placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground),
        )
}
