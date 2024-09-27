/**
 * Supporting classes for LTI assessment and grades service. 
 *
 * References:
 * 
 * Learning Tools Interoperability (LTI) Assignment and Grade Services Specification
 * https://www.imsglobal.org/spec/lti-ags/v2p0
 * 
 * Learning Tools Interoperability(LTI)® Assignment and Grade Services Version 2.0 OpenAPI Specs
 * https://www.imsglobal.org/spec/lti-ags/v2p0/openapi/
 * 
 * Data types used in openapi
 * https://github.com/OAI/OpenAPI-Specification/blob/main/versions/3.1.0.md#data-types
 * E.g. adds 'float' and 'double' formats to standard 'number' type. (But which
 * is considered the default? Where are they defined? Are they supposed to work with IEEE 754?)
 * 
 * Which is built on data types in JSON schema validation:
 * https://datatracker.ietf.org/doc/html/draft-bhutton-json-schema-validation-00
 * E.g. Defines 'number' and 'integer'.
 * 
 * @since 0.0.1
 */
package uk.ac.leedsbeckett.lti.services.ags;