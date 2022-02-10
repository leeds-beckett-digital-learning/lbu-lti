/*
 * Copyright 2022 Leeds Beckett University.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.ac.leedsbeckett.lti.state;

import java.util.HashMap;
import uk.ac.leedsbeckett.lti.LtiConfiguration;

/**
 * A utility class that stores LTI state. This will probably be subclassed
 * by a tool implementation.
 * 
 * @author jon
 */
public class LtiStateStore
{
  public static final long TIMEOUTSECONDS = 60;
  
  HashMap<String,LtiState> map = new HashMap<>();
  
  /**
   * Subclasses will override this method if they want to use a state class
   * which is a subclass of LtiState.
   * 
   * @param client The client configuration.
   * @return An object of type LtiState or subclass thereof.
   */
  protected LtiState newState( LtiConfiguration.Client client )
  {
    return new LtiState( client );    
  }
  
  /**
   * Instantiate and store a new state object.
   * 
   * @param client The client configuration.
   * @return An object of type LtiState or subclass thereof.
   */
  public LtiState createState( LtiConfiguration.Client client )
  {
    LtiState state = newState( client );
    synchronized ( map )
    {
      map.put( state.id, state );
    }
    return state;
  }
  
  /**
   * Get a state object keyed by its unique ID.
   * 
   * @param id The unique ID of the required state object.
   * @return The state object or null if not found or timed out.
   */
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
  
