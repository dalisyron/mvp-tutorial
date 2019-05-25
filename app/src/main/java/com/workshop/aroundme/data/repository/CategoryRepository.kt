
package com.workshop.aroundme.data.repository

import com.workshop.aroundme.data.model.ParentCategoryEntity
import com.workshop.aroundme.remote.datasource.CategoryRemoteDataSource
import io.reactivex.Single
import javax.inject.Inject

class CategoryRepository @Inject constructor(private val categoryRemoteDataSource: CategoryRemoteDataSource) {

    fun getCategories(): Single<List<ParentCategoryEntity>> {
        return Single.fromCallable {
            categoryRemoteDataSource.getCategories()
        }
    }
}
