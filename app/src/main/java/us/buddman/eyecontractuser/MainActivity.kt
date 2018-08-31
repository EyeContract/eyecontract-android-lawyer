package us.buddman.eyecontractuser

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import us.buddman.eyecontractuser.fragments.ChatFragment
import us.buddman.eyecontractuser.fragments.DocumentFragment
import us.buddman.eyecontractuser.fragments.MyInfoFragment
import us.buddman.eyecontractuser.fragments.TemplateFragment
import us.buddman.eyecontractuser.utils.FragmentUtils


class MainActivity : AppCompatActivity() {

    lateinit var mDocumentFragment: DocumentFragment
    lateinit var mTemplateFragment: TemplateFragment
    lateinit var mChatFragment: ChatFragment
    lateinit var mMyInfoFragment: MyInfoFragment

    val mFragments: FragmentUtils by lazy {
        mDocumentFragment = DocumentFragment()
        mTemplateFragment = TemplateFragment()
        mChatFragment = ChatFragment()
        mMyInfoFragment = MyInfoFragment()
        FragmentUtils(R.id.mainFragmentContainer,
                arrayOf(mDocumentFragment, mTemplateFragment, mChatFragment, mMyInfoFragment))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initFragment()
    }

    private fun initFragment() {
        mFragments.setCurrentFragmentByPosition(supportFragmentManager, 0, Bundle())
        mainBottomBar.setOnTabSelectListener { tabId: Int ->
            val position: Int
            when (tabId) {
                R.id.main_document -> {
                    position = 0
                }
                R.id.main_template -> {
                    position = 1
                }
                R.id.main_chat -> {
                    position = 2
                }
                R.id.main_my -> {
                    position = 3
                }
                else -> {
                    position = 3
                }
            }
            mFragments.setCurrentFragmentByPosition(supportFragmentManager, position, Bundle())
        }
    }
}
