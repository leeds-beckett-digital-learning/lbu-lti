/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.ac.leedsbeckett.lti.registration;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;

/**
 *
 * @author jon
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LtiSupportedMessage implements Serializable
{
  String type;
  String[] placements;

  public String getType()
  {
    return type;
  }

  public void setType( String type )
  {
    this.type = type;
  }

  public String[] getPlacements()
  {
    return placements;
  }

  public void setPlacements( String[] placements )
  {
    this.placements = placements;
  }
}
