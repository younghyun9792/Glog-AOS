package com.example.glog

//import android.arch.persistence.room.*
import android.content.Context
import androidx.room.*

@Entity
data class UserInfo(
    @PrimaryKey(autoGenerate = true)
    val uid: Int,
    val id:String,
    val password:String,
    val nickname: String
)

@Entity
data class Board(
    @PrimaryKey(autoGenerate = true)
    val bid: Int,
    val title: String,
    val title_picture: String,
    val content: String,
    val writer: String,
    val write_uid: Int,
    val tag: String,
    val hart: Int,
    val write_time: String,
    val hit: Int
)

@Entity
data class Comment(
    @PrimaryKey(autoGenerate = true)
    val cid: Int,
    val write_bid: Int,
    val writer: String,
    val writer_uid: Int,
    val content: String,
    val write_time: String
)

@Dao
interface UserDao {
    // User
    @Insert
    fun insertUser(userInfo: UserInfo) //

    @Query("select * from UserInfo") // 회원가입
    fun getAllUser(): List<UserInfo>

    @Query("select id from UserInfo where id = :id")
    fun getId(id: String): String

    @Query("select nickname from UserInfo where nickname = :nickname")
    fun getNickname(nickname: String): String

    @Query("select id from UserInfo where id = :id and password = :password") // 로그인 시도했을 때 유저가 있으면 결과가 반환되고, 없으면 null
    fun isLoginById(id: String, password: String): String?

    @Query("update UserInfo set id = :id and password = :password and nickname = :nickname where id = :id") //
    fun UpdateProfile(id: String, password: String, nickname: String)

    @Query("delete from UserInfo")
    fun deleteAllUser()
}

@Dao
interface BoardDao {

    @Insert
    fun insertBoard(Board: Board) // 게시글 작성 ( Create )

    @Query("select * from Board") // 게시글 확인할 때 ( Read )
    fun getBoard(): List<Board>

    @Query("select * from Board where bid = :bid")
    fun getBoardBybid(bid: Int): List<Board>

    @Query("update Board set title = :title and content = :content and tag = :tag where bid = :bid") // 게시글 수정 ( Update )
    fun updateBoard(title: String, content:String, tag:String, bid: Int)

    @Query("update Board set hart = hart + 1 where bid = :bid") // 좋아요(하트) 눌렀을 때
    fun hartadd(bid: Int)

    @Query("delete from Board where bid = :bid") // 게시글 삭제 ( Delete )
    fun deleteBoard(bid: Int)
}

@Dao
interface CommentDao {

    @Insert
    fun insertComment(Comment: Comment) // 댓글 작성 ( Create )

    @Query("select * from Comment where write_bid=:bid")
    fun getCommentBybid(bid: Int): List<Comment>

    @Query("update Comment set content = :content where cid = :cid")
    fun updateComment(content:String, cid: Int)


    @Query("delete from Comment where cid = :cid") // 댓글 삭제 ( Delete )
    fun deleteComment(cid: Int)
}

@Database(entities = arrayOf(UserInfo::class, Board::class, Comment::class), version = 1)
abstract class Glogdb: RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun boardDao() : BoardDao
    abstract fun commentDao() : CommentDao

    companion object {
        private var instance: Glogdb? = null

        @Synchronized
        fun getInstance(context: Context): Glogdb? {
            if (instance == null) {
                synchronized(Glogdb::class){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        Glogdb::class.java,
                        "user-database"
                    ).build()
                }
            }
            return instance
        }
    }
}