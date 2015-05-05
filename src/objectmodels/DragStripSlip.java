/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objectmodels;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Mark
 */
public class DragStripSlip extends BaseObjectModel implements Serializable{
    // eighth
    private Date date;
    private Float reactionTime;
    private Float sixtyFeetTime;
    private Float threeHundredThirtyFeetTime;
    private Float eighthMileTime;
    private Float eighthSpeed;
    private Float oneThousandFeetTime;
    private Float quarterMileTime;
    private Float quarterMileSpeed;

    /**
     * Main Constructor
     * @param incomingDate
     * @param incomingReactionTime
     * @param incomingSixtyFeetTime
     * @param incomingThreeHundredThirtyFeetTime
     * @param incomingEighthSpeed
     * @param incomingEighthMileTime
     * @param incomingOneThousandFeetTime
     * @param incomingQuarterMileSpeed
     * @param incomingQuarterMileTime
     */
    public DragStripSlip(Date incomingDate,Float incomingReactionTime, Float incomingSixtyFeetTime, Float incomingThreeHundredThirtyFeetTime, Float incomingEighthMileTime, Float incomingEighthSpeed
    ,Float incomingOneThousandFeetTime, Float incomingQuarterMileTime, Float incomingQuarterMileSpeed){

        this.date = incomingDate;
        
        this.reactionTime = incomingReactionTime;
        this.sixtyFeetTime = incomingSixtyFeetTime;
        this.threeHundredThirtyFeetTime = incomingThreeHundredThirtyFeetTime;
        this.eighthMileTime = incomingEighthMileTime;
        this.eighthSpeed = incomingEighthSpeed;
        this.oneThousandFeetTime = incomingOneThousandFeetTime;
        this.quarterMileTime = incomingQuarterMileTime;
        this.quarterMileSpeed = incomingQuarterMileSpeed;       
    }
    
    
    
    /**
     * Method used for the sole purpose of filling out a JTable in MainWindow
     * @return 
     */
    public Object[] getDragStripSlipObject(){
        DateFormat dateInstance = SimpleDateFormat.getDateInstance();
        //System.out.println(dateInstance.format(Calendar.getInstance().getTime()));
        //System.out.println(dateInstance.format(this.date));
        
        String[] stringArray = {dateInstance.format(this.date),this.reactionTime.toString(), this.sixtyFeetTime.toString(), this.threeHundredThirtyFeetTime.toString(),
            this.eighthMileTime.toString(),this.eighthSpeed.toString(),this.oneThousandFeetTime.toString(),this.quarterMileTime.toString(),this.quarterMileSpeed.toString()};
        return stringArray;
    }
    
    public void setDate(Date incomingDate){
        this.date = incomingDate;
    }
    
    public Date getDate(){
        return this.date;
    }
    
    public void setReactionTime(Float incomingReactionTime){
        this.reactionTime = incomingReactionTime;
    }
    
    public Float getReactionTime(){
        return this.reactionTime;
    }
    
    public void setSixtyFeetTime(Float incomingSixtyFeetTime){
        this.sixtyFeetTime = incomingSixtyFeetTime;
    }
    
    public Float getSixtyFeetTime(){
        return this.sixtyFeetTime;
    }
    
    public void setThreeHundredThirtyFeetTime(Float incomingThreeHundredThirtyFeetTime){
        this.threeHundredThirtyFeetTime = incomingThreeHundredThirtyFeetTime;
    }
    
    public Float getThreeHundredThirtyFeetTime(){
        return this.threeHundredThirtyFeetTime;
    }
    
    public void setEighthMileTime(Float incomingEighthMileTime){
        this.eighthMileTime = incomingEighthMileTime;
    }
    
    public Float getEighthMileTime(){
        return this.eighthMileTime;
    }
    
    public void setEighthSpeed(Float incomingEighthSpeed){
        this.eighthSpeed = incomingEighthSpeed;
    }
    
    public Float getEighthSpeed(){
        return this.eighthSpeed;
    }
    
    public void setOneThousandFeetTime(Float incomingOneThousandFeetTime){
        this.oneThousandFeetTime = incomingOneThousandFeetTime;
    }
    
    public Float getOneThousandFeetTime(){
        return this.oneThousandFeetTime;
    }
        
        
    public void setQuarterMileTime(Float incomingQuarterMileTime){
        this.quarterMileTime = incomingQuarterMileTime;
    }
    
    public Float getQuarterMileTime(){
        return this.quarterMileTime;
    }
    
    public void setQuarterMileSpeed(Float incomingQuarterMileSpeed){
        this.quarterMileSpeed = incomingQuarterMileSpeed;
    }
    
    public Float getQuarterMileSpeed(){
        return this.quarterMileSpeed;
    }
    
    
}
