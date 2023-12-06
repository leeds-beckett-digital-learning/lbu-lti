/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.ac.leedsbeckett.lti.resourcelink;

import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author jon
 */
public class LtiResourceLink implements Serializable
{
  String type = "ltiResourceLink";
  String title;
  String text;
  String url;
  HashMap<String,String> custom = new HashMap<>();

  public LtiResourceLink()
  {
  }
  
  public String getType()
  {
    return type;
  }

  public void setType( String type )
  {
    this.type = type;
  }

  public String getTitle()
  {
    return title;
  }

  public void setTitle( String title )
  {
    this.title = title;
  }

  public String getText()
  {
    return text;
  }

  public void setText( String text )
  {
    this.text = text;
  }

  public String getUrl()
  {
    return url;
  }

  public void setUrl( String url )
  {
    this.url = url;
  }

  public HashMap<String, String> getCustom()
  {
    return custom;
  }

  public void setCustom( HashMap<String, String> custom )
  {
    this.custom = custom;
  }
  
  public void putCustom( String name, String value )
  {
    custom.put( name, value );
  }
}

