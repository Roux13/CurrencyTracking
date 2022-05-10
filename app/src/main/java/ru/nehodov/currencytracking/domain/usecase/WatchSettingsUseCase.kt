package ru.nehodov.currencytracking.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.nehodov.currencytracking.data.preferences.entity.AppSettingsEntity
import ru.nehodov.currencytracking.domain.repository.AppSettingsRepository
import javax.inject.Inject

class WatchSettingsUseCase @Inject constructor(
    private val appSettingsRepository: AppSettingsRepository
) : UseCase<Unit, Flow<AppSettingsEntity>> {
    override fun invoke(params: Unit): Flow<AppSettingsEntity> {
        return appSettingsRepository.settingsFlow()
    }
}