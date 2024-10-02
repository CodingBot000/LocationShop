package com.codingbot.shop.di

import com.codingbot.shop.datasource.DataSourceCommon
import com.codingbot.shop.datasource.DataSourceCommonImpl
import com.codingbot.shop.datasource.DataSourceFavorite
import com.codingbot.shop.datasource.DataSourceFavoriteImpl
import com.codingbot.shop.repository.RepositoryCommon
import com.codingbot.shop.repository.RepositoryCommonImpl
import com.codingbot.shop.repository.RepositoryFavorite
import com.codingbot.shop.repository.RepositoryFavoriteImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    @Singleton
    fun bindRepositoryCommon(repositoryCommmon: RepositoryCommonImpl): RepositoryCommon

    @Binds
    @Singleton
    fun bindDataSourceCommon(dataSourceCommon: DataSourceCommonImpl): DataSourceCommon

    @Binds
    @Singleton
    fun bindRepositoryFavorite(repositoryFavorite: RepositoryFavoriteImpl): RepositoryFavorite

    @Binds
    @Singleton
    fun bindDataSourceFavorite(dataSourceFavorite: DataSourceFavoriteImpl): DataSourceFavorite

}
