package com.codingbot.shop.di

import com.codingbot.shop.data.repository.RepositoryCommonImpl
import com.codingbot.shop.data.repository.RepositoryCommon
import com.codingbot.shop.data.repository.RepositoryEvent
import com.codingbot.shop.data.repository.RepositoryEventImpl
import com.codingbot.shop.data.repository.RepositoryFavorite
import com.codingbot.shop.data.repository.RepositoryFavoriteImpl
import com.codingbot.shop.data.repository.RepositoryProductData
import com.codingbot.shop.data.repository.RepositoryProductDataImpl
import com.codingbot.shop.data.repository.RepositoryReview
import com.codingbot.shop.data.repository.RepositoryReviewImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindRepositoryCommon(repositoryCommmon: RepositoryCommonImpl): RepositoryCommon

    @Binds
    @Singleton
    fun bindRepositoryFavorite(repositoryFavorite: RepositoryFavoriteImpl): RepositoryFavorite

    @Binds
    @Singleton
    fun bindRepositoryProduct(repositoryProductData: RepositoryProductDataImpl): RepositoryProductData

    @Binds
    @Singleton
    fun bindRepositoryReview(repositoryReview: RepositoryReviewImpl): RepositoryReview

    @Binds
    @Singleton
    fun bindRepositoryEvent(repositoryEvent: RepositoryEventImpl): RepositoryEvent

}
