/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.ac.leedsbeckett.lti.state;

import java.util.HashMap;
import uk.ac.leedsbeckett.lti.LtiConfiguration;

/**
 *
 * @author jon
 */
public class LtiStateStore
{
  public static final long TIMEOUTSECONDS = 60;
  
  HashMap<String,LtiState> map = new HashMap<>();
  
  protected LtiState newState( LtiConfiguration.Client client )
  {
    return new LtiState( client );    
  }
  
  public LtiState createState( LtiConfiguration.Client client )
  {
    LtiState state = newState( client );
    synchronized ( map )
    {
      map.put( state.id, state );
    }
    return state;
  }
  
  public LtiState getState( String id )
  {
    LtiState state = null;
    synchronized ( map )
    {
      long now = System.currentTimeMillis();
      state = map.get( id );
      if ( state != null && (now - state.timestamp) > (TIMEOUTSECONDS*1000L) )
      {
        map.remove( id );
        state = null;
      }
    }
    return state;
  }
}
  
