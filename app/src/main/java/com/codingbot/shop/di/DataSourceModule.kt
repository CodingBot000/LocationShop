package com.codingbot.shop.di

import com.codingbot.shop.data.datasource.DataSourceCommonImpl
import com.codingbot.shop.data.datasource.DataSourceCommon
import com.codingbot.shop.data.datasource.DataSourceEvent
import com.codingbot.shop.data.datasource.DataSourceEventImpl
import com.codingbot.shop.data.datasource.DataSourceFavorite
import com.codingbot.shop.data.datasource.DataSourceFavoriteImpl
import com.codingbot.shop.data.datasource.DataSourceProductData
import com.codingbot.shop.data.datasource.DataSourceProductDataImpl
import com.codingbot.shop.data.datasource.DataSourceReview
import com.codingbot.shop.data.datasource.DataSourceReviewImpl

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Binds
    @Singleton
    fun bindDataSourceCommon(dataSourceCommon: DataSourceCommonImpl): DataSourceCommon

    @Binds
    @Singleton
    fun bindDataSourceFavorite(dataSourceFavorite: DataSourceFavoriteImpl): DataSourceFavorite

    @Binds
    @Singleton
    fun bindDataSourceProductData(dataSourceProductData: DataSourceProductDataImpl): DataSourceProductData

    @Binds
    @Singleton
    fun bindDataSourceReview(dataSourceReview: DataSourceReviewImpl): DataSourceReview

    @Binds
    @Singleton
    fun bindDataSourceEvent(dataSourceEvent: DataSourceEventImpl): DataSourceEvent
}
