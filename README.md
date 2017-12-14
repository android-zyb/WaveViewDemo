# WaveViewDemo
双层振幅较小的波浪效果
可以修改振幅，动画时间，波浪线颜色等
    /**
     * 设置波浪的x轴幅度
     * 一上一下两个弧度的宽度 （默认为1800）
     *
     * @param waveWidth
     */
    public void setWaveWidth (int waveWidth) {
        this.waveWidth = waveWidth;
    }
    
    
   /**
     * 设置波浪的Y轴的波动幅度  （默认为50）
     *
     * @param waveHeight
     */
    public void setWaveHeight (int waveHeight) {
        this.waveHeight = waveHeight;
    }
    
    /**
     * 设置波浪的流动时间，以毫秒为单位  值越大，变换的速度越慢
     *
     * @param durationTime
     */
    public void setDurationTime (int durationTime) {
        this.durationTime = durationTime;
    }
    
    //可以跟随Activity生命周期来控制动画
    //开启动画
    mWaveView.startWave();
    
    //关闭动画
    mWaveView.stopWave();
    
