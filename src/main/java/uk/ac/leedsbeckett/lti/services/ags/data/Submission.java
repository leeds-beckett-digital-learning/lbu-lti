/*
 * Copyright 2024 maber01.
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
package uk.ac.leedsbeckett.lti.services.ags.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.Instant;
import uk.ac.leedsbeckett.lti.json.Iso8601InstantDeserializer;
import uk.ac.leedsbeckett.lti.json.Iso8601InstantSerializer;

/**
 * Submission related dates for Score data object.
 * 
 * @author maber01
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Submission
{
  private final Instant startedAt;
  private final Instant submittedAt;

  public Submission( 
          @JsonProperty("startedAt")   @JsonDeserialize(using = Iso8601InstantDeserializer.class) Instant startedAt, 
          @JsonProperty("submittedAt") @JsonDeserialize(using = Iso8601InstantDeserializer.class) Instant submittedAt )
  {
    this.startedAt = startedAt;
    this.submittedAt = submittedAt;
  }

  @JsonSerialize(using = Iso8601InstantSerializer.class)
  public Instant getStartedAt()
  {
    return startedAt;
  }

  @JsonSerialize(using = Iso8601InstantSerializer.class)
  public Instant getSubmittedAt()
  {
    return submittedAt;
  }
}
