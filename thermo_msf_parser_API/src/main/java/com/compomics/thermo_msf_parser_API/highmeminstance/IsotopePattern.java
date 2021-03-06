package com.compomics.thermo_msf_parser_API.highmeminstance;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Niklaas
 * Date: 21-Feb-2011
 * Time: 11:17:36
 */

/**
 * This class represents the isotope pattern with event annotations attached to it
 *
 * @author Davy Maddelein
 * @version $Id: $Id
 */
public class IsotopePattern {
    /**
     * The isotope pattern id
     */
    private int iIsotopePatternId;
    /**
     * The attached event annotations
     */
    private List<EventAnnotation> iEventAnnotations = new ArrayList<EventAnnotation>();

    /**
     * The isotope pattern constructor
     *
     * @param iIsotopePatternId The isotope pattern id
     */
    public IsotopePattern(int iIsotopePatternId) {
        this.iIsotopePatternId = iIsotopePatternId;
    }


    /**
     * Getter for the isotope pattern id
     *
     * @return int with the isotope pattern id
     */
    public int getIsotopePatternId() {
        return iIsotopePatternId;
    }

    /**
     * Getter for the attached event annotations
     *
     * @return List with the attached event annotations
     */
    public List<EventAnnotation> getEventAnnotations() {
        return iEventAnnotations;
    }

    /**
     * Getter for the shared quan channel id
     * This will look into the event annotations and will find the channel id
     *
     * @return int with the channel id
     */
    public int getSharedQuanChannelId(){
        int lChannelId = -1;
        for(int i = 0; i<iEventAnnotations.size(); i ++){
            if(iEventAnnotations.get(i).getQuanChannelId() != -1){
                lChannelId = iEventAnnotations.get(i).getQuanChannelId();
            }
        }
        return lChannelId;

    }

    /**
     * Getter for the shared quan event id
     * This will look into the event annotations and will find the quan event id
     *
     * @return int with the channel id
     */
    public int getSharedQuanResultId(){
        int lResultId = -1;
        for(int i = 0; i<iEventAnnotations.size(); i ++){
            if(iEventAnnotations.get(i).getQuanResultId() != -1){
                lResultId = iEventAnnotations.get(i).getQuanResultId();
            }
        }
        return lResultId;

    }

    /**
     * Getter for the events linked to the event annotations
     *
     * @param lConn A connection to the msf file
     * @return List with the events linked to the attached
     * @throws java.sql.SQLException An exception is thrown whenever there is a problem with the connection to the msf file
     */
    public List<Event> getEvents(Connection lConn) throws SQLException {
        List<Integer> lIds = new ArrayList<Integer>();
        for(int i = 0; i<iEventAnnotations.size(); i++){
            lIds.add(iEventAnnotations.get(i).getEventId());
        }
        return Event.getEventByIds(lIds, lConn);
    }

    /**
     * Getter for the events with a quan result linked to the event annotations
     *
     * @param lConn A connection to the msf file
     * @return List with the events linked to the attached
     * @throws java.sql.SQLException An exception is thrown whenever there is a problem with the connection to the msf file
     */
    public List<Event> getEventsWithQuanResult(Connection lConn) throws SQLException {
        List<Integer> lIds = new ArrayList<Integer>();
        for(int i = 0; i<iEventAnnotations.size(); i++){
            if(iEventAnnotations.get(i).getQuanResultId() != -1){
                lIds.add(iEventAnnotations.get(i).getEventId());
            }
        }
        return Event.getEventByIds(lIds, lConn);
    }

    /**
     * Getter for the events without a quan result linked to the event annotations
     *
     * @param lConn A connection to the msf file
     * @return List with the events linked to the attached
     * @throws java.sql.SQLException An exception is thrown whenever there is a problem with the connection to the msf file
     */
    public List<Event> getEventsWithoutQuanResult(Connection lConn) throws SQLException {
        List<Integer> lIds = new ArrayList<Integer>();
        for(int i = 0; i<iEventAnnotations.size(); i++){
            if(iEventAnnotations.get(i).getQuanChannelId() == -1){
                lIds.add(iEventAnnotations.get(i).getEventId());
            }
        }
        return Event.getEventByIds(lIds, lConn);
    }


    /**
     * This method will add an event annotation to this class
     *
     * @param lEventAnno An event annotation
     */
    public void addEventAnnotation(EventAnnotation lEventAnno){
        this.iEventAnnotations.add(lEventAnno);
    }
}
