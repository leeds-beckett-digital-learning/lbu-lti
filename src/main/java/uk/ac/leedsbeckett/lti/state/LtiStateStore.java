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

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.cache.Cache;
import uk.ac.leedsbeckett.lti.config.ClientLtiConfigurationKey;

/**
 * A utility class that stores LTI state. This will be subclassed
 * by a tool or framework implementation that will declare the actual
 * type of the LtiState that will be used.
 * 
 * @author jon
 * @param <T> The subtype of the LtiState that will be stored by implementations of store.
 */
public class LtiStateStore<T extends LtiState>
{
  static final Logger logger = Logger.getLogger(LtiStateStore.class.getName());
  
  public static final long TIMEOUTSECONDS = 60;

  private final LtiStateSupplier<T> supplier;
  private final Cache<String,T> cache;
  
  /**
   * To construct this class application code needs to supply a cache and
   * an object that can instantiate cache entries of the correct type.
   * 
   * @param cache A cache implementation to use in this store.
   * @param supplier An object that can instantiate new state objects.
   */
  public LtiStateStore( Cache<String,T> cache, LtiStateSupplier<T> supplier )
  {
    this.cache  = cache;
    this.supplier = supplier;
  }
  
  /**
   * Instantiate and store a new state object.
   * 
   * @param clientKey Identifies client LTI configuration for the tool in question.
   * @return An object of type LtiState or subclass thereof.
   */
  public T createState( ClientLtiConfigurationKey clientKey )
  {
    T state = supplier.get( clientKey );
    assert( state != null );
    logger.log(Level.FINE, "Creating LtiState with ID {0}", state.getId());
    cache.put( state.getId(), state );
    if ( !cache.containsKey( state.getId() ) )
      logger.log( Level.SEVERE, "LtiState was not successfully stored in the cache." );
    return state;
  }

  /**
   * Replace the version in the cache.
   * 
   * @param state A state object to put in the cache.
   */
  public void updateState( T state )
  {
    cache.put( state.getId(), state );
  }
  
  /**
   * Get a state object keyed by its unique ID.
   * 
   * @param id The unique ID of the required state object.
   * @return The state object or null if not found or timed out.
   */
  public T getState( String id )
  {
    long now = System.currentTimeMillis();
    T state = cache.get( id );
    if ( state == null )
      logger.log( Level.FINE, "Did not find LtiState with ID {0}", id );
    else
    {
      if ( (now - state.timestamp) > (TIMEOUTSECONDS*1000L) )
      {
        logger.log( Level.FINE, "Found ID {0} but it has expired.", id );
        cache.remove( id );
        state = null;
      }
      else
        logger.log( Level.FINE, "Found ID {0}.", id );
    }
    
    return state;
  }
}
  
