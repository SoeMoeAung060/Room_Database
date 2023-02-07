package android.example.myroomdatabase.Repository

import android.example.myroomdatabase.data.UserDao
import android.example.myroomdatabase.model.User
import androidx.lifecycle.LiveData


// A repository class abstracts access to multiple data sources.
// The repository in not part of the Architecture Components libraries,
// but is a suggested best practice for code separation and architecture.

//为什么用Repository
//Repository是用于管理多个数据资源，例如数据库，网络等
//Repository管理数据的查询线程，同时，可以使用多个后端。在常规情况下，
// Repository主要实现从服务端拉取数据还是从本地数据库拉取数据的逻辑。
class UserRepository(private val userDao: UserDao) {

    val readAllData : LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }

    suspend fun upDateUser(user: User){
        userDao.upDate(user)
    }

    suspend fun deleteUser(user: User){
        userDao.delete(user)
    }

    suspend fun deleteAllUser(){
        userDao.deleteAllUser()
    }
}