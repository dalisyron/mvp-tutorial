package com.workshop.aroundme.remote.datasource

import com.workshop.aroundme.data.mapper.toCategoryEntity
import com.workshop.aroundme.data.model.ParentCategoryEntity
import com.workshop.aroundme.remote.service.CategoryService
import javax.inject.Inject

class CategoryRemoteDataSource @Inject constructor(private val categoryService: CategoryService) {

    fun getCategories(): List<ParentCategoryEntity>? {
        return categoryService.getCategoriesResponse()
            .response
            ?.map {
                ParentCategoryEntity(
                    name = it.key,
                    children = it.value
                        .map { categoryDto ->
                            categoryDto.toCategoryEntity()
                        }
                )
            }
    }
}