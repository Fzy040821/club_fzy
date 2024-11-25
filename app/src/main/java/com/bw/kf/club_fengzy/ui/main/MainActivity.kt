package com.bw.kf.club_fengzy.ui.main

import android.widget.RadioButton
import androidx.activity.viewModels
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.alibaba.android.arouter.facade.annotation.Route
import com.bw.kf.club_fengzy.BuildConfig
import com.bw.kf.club_fengzy.R
import com.bw.kf.club_fengzy.Router
import com.bw.kf.club_fengzy.base.BaseActivity
import com.bw.kf.club_fengzy.databinding.ActivityMainBinding
import com.bw.kf.club_fengzy.ui.main.club.ClubHomeFragmentV2
import com.bw.kf.club_fengzy.ui.main.club.MotorClubNotJoinedFragment
import com.bw.kf.club_fengzy.ui.main.mall.MallHomeFragment
import com.bw.kf.club_fengzy.ui.main.message.MessageFragment
import com.bw.kf.club_fengzy.ui.main.mine.MineFragment
import com.bw.kf.club_fengzy.ui.main.moto.MotoCircleV3Fragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@Route(path = Router.Ui.MainActivity)
class MainActivity : BaseActivity<MainViewModel , ActivityMainBinding>() {
    override val mViewModel: MainViewModel by viewModels()
    override val mLayoutResId: Int = R.layout.activity_main

    companion object{
        private const val MY_CAR_FRAGMENT ="myCar"
        private const val MOTOR_CIRCLE_FRAGMENT = "friends"
        private const val MOTOR_CLUB_FRAGMENT = "club"
        private const val MOTOR_CLUB_FRAGMENT_CHECKING = "club_checking"
        private const val MOTOR_CLUB_FRAGMENT_NOT_JOINED = "club_not_joined"
        private const val MINE_FRAGMENT = "mine"
        private const val MALL_FRAGMENT = "mall"
        private const val MESSAGE_FRAGMENT = "message"

        const val MESSAGE_REGISTRATION = "com.jczy.cyclone.MESSAGE_REGISTRATION_ID"
    }

    private val mMotoCircleFragment by lazy {
        MotoCircleV3Fragment()
    }

    private val mMotoClubNotJoinedFragment: Fragment by lazy {
        if(BuildConfig.DEBUG){
            ClubHomeFragmentV2()
        }else{
            MotorClubNotJoinedFragment()
        }
    }

    private val mMineFragment by lazy {
        MineFragment()
    }

    private val mMallFragment by lazy {
        MallHomeFragment()
    }

    private val mMessageFragment by lazy {
        MessageFragment()
    }

    override fun initListener() {
    }

    override fun initData() {
    }

    override fun initView() {
        super.initView()
        //默认进入玩车页面
        navToFragment(R.id.nav_motor_circle)
        (mBinding.rgBottom[1] as RadioButton).isChecked = true
    }

    private fun navToFragment(id: Int){
        val bt = supportFragmentManager.beginTransaction()
        hideCurrentFragment(bt)
        when(id){
            R.id.nav_motor_circle ->{
                if(supportFragmentManager.findFragmentByTag(MOTOR_CIRCLE_FRAGMENT)?.isAdded!=true){
                    bt.add(R.id.fragment_container , mMotoCircleFragment , MOTOR_CIRCLE_FRAGMENT)
                }else{
                    bt.show(mMotoCircleFragment)
                }
                bt.commitAllowingStateLoss()
            }

            R.id.nav_motor_club ->{
                analysisClubFragment(bt)
            }

            R.id.nav_mine ->{
                if(supportFragmentManager.findFragmentByTag(MINE_FRAGMENT)?.isAdded != true){
                    bt.add(R.id.fragment_container , mMineFragment , MINE_FRAGMENT)
                }else{
                    bt.show(mMineFragment)
                }
                bt.commitAllowingStateLoss()
            }
            R.id.nav_motor_mall->{
                if(supportFragmentManager.findFragmentByTag(MALL_FRAGMENT)?.isAdded != true){
                    bt.add(R.id.fragment_container , mMallFragment , MALL_FRAGMENT)
                }else{
                    bt.show(mMineFragment)
                }
                bt.commitAllowingStateLoss()
            }
            R.id.nav_message->{
                if(supportFragmentManager.findFragmentByTag(MESSAGE_FRAGMENT)?.isAdded != true){
                    bt.add(R.id.fragment_container , mMessageFragment , MESSAGE_FRAGMENT)
                }else{
                    bt.show(mMessageFragment)
                }
                bt.commitAllowingStateLoss()
            }
        }
    }

    private fun hideCurrentFragment(bt: FragmentTransaction){
        if(supportFragmentManager.findFragmentByTag(MOTOR_CIRCLE_FRAGMENT)?.isVisible == true){
            bt.hide(mMotoCircleFragment)
        }
        hideClubFragment(bt)
        if(supportFragmentManager.findFragmentByTag(MINE_FRAGMENT)?.isVisible == true){
            bt.hide(mMineFragment)
        }
        if(supportFragmentManager.findFragmentByTag(MALL_FRAGMENT)?.isVisible == true){
            bt.hide(mMallFragment)
        }
        if(supportFragmentManager.findFragmentByTag(MESSAGE_FRAGMENT)?.isVisible == true){
            bt.hide(mMessageFragment)
        }
    }

    /**
     * 隐藏俱乐部相关Fragment
     */
    private fun hideClubFragment(bt: FragmentTransaction){
        if(supportFragmentManager.findFragmentByTag(MOTOR_CLUB_FRAGMENT_NOT_JOINED)?.isVisible == true){
            bt.hide(mMotoClubNotJoinedFragment)
        }
    }

    /**
     * 分析俱乐部Fragment
     */
    private fun analysisClubFragment(bt: FragmentTransaction){
        /** 未加入任何俱乐部**/

        if(supportFragmentManager.findFragmentByTag(MOTOR_CLUB_FRAGMENT_NOT_JOINED)?.isAdded!= true){
            bt.add(R.id.fragment_container , mMotoClubNotJoinedFragment , MOTOR_CLUB_FRAGMENT_NOT_JOINED)
        }else{
            bt.show(mMotoClubNotJoinedFragment)
        }
        bt.commitAllowingStateLoss()
    }

}