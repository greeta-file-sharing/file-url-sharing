/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.33).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.model.Error;
import io.swagger.model.File;
import io.swagger.model.Files;
import io.swagger.model.User;

import org.springframework.core.io.Resource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

import com.amazonaws.services.s3.model.ListObjectsV2Result;

import java.io.IOException;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-02-27T07:39:21.717Z[GMT]")
@Validated
public interface FilesApi {

    @Operation(summary = "Creates a file Object with meta data but not the file yet", description = "", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "files" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "201", description = "Null response"),
        
        @ApiResponse(responseCode = "401", description = "Authentication information is missing or invalid"),
        
        @ApiResponse(responseCode = "200", description = "unexpected error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))) })
    @RequestMapping(value = "/files",
        produces = { "application/json" }, 
        consumes = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<File> createFile(
        @Parameter(in = ParameterIn.DEFAULT, description = "Send the File Object not the File content", required=true, schema=@Schema()) @Valid @RequestBody File body,
        @RequestAttribute("user") User user
    );


    @Operation(summary = "Download a file", description = "", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "files" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "201", description = "Download File response", content = @Content(mediaType = "application/octet-stream", schema = @Schema(implementation = Resource.class))),
        
        @ApiResponse(responseCode = "401", description = "Authentication information is missing or invalid"),
        
        @ApiResponse(responseCode = "200", description = "unexpected error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))) })
    @RequestMapping(value = "/files/{fileId}/download",
        produces = { "application/octet-stream", "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<byte[]> downloadFile(
        @Parameter(in = ParameterIn.PATH, description = "The id of the file to retrieve", required=true, schema=@Schema()) @PathVariable("fileId") String fileId,
        @Parameter(in = ParameterIn.QUERY, description = "" ,schema=@Schema()) @Valid @RequestParam(value = "token", required = false) String token,
        @RequestAttribute("user") User user
    ) throws IOException;

    @Operation(summary = "Delete a file", description = "", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "files" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Delete File response"),      
        @ApiResponse(responseCode = "401", description = "Authentication information is missing or invalid")
    })
    @RequestMapping(value = "/files/{fileId}", method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteFileById(@Parameter(in = ParameterIn.PATH, description = "The id of the file to retrieve", required=true, schema=@Schema()) @PathVariable("fileId") String fileId);

    @Operation(summary = "List all files", description = "", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "files" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "A paged array of files", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Files.class))),
        
        @ApiResponse(responseCode = "401", description = "Authentication information is missing or invalid"),
        
        @ApiResponse(responseCode = "200", description = "unexpected error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))) })
    @RequestMapping(value = "/files",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<ListObjectsV2Result> listFiles(@Parameter(in = ParameterIn.QUERY, description = "How many items to return at one time (max 100)" ,schema=@Schema()) @Valid @RequestParam(value = "limit", required = false) Integer limit);


    @Operation(summary = "Recovers a deleted file", description = "", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "files" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "201", description = "Upload File response", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = Boolean.class))),
        
        @ApiResponse(responseCode = "401", description = "Authentication information is missing or invalid"),
        
        @ApiResponse(responseCode = "200", description = "unexpected error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))) })
    @RequestMapping(value = "/files/{fileId}/recover",
        produces = { "text/plain", "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<Boolean> recoverFile(@Parameter(in = ParameterIn.PATH, description = "The id of the file to recover", required=true, schema=@Schema()) @PathVariable("fileId") String fileId);


    @Operation(summary = "Generate a shareable URL for given file", description = "", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "files" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "201", description = "Download File response", content = @Content(mediaType = "application/octet-stream", schema = @Schema(implementation = Resource.class))),
        
        @ApiResponse(responseCode = "401", description = "Authentication information is missing or invalid"),
        
        @ApiResponse(responseCode = "200", description = "unexpected error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))) })
    @RequestMapping(value = "/files/{fileId}/share",
        produces = { "application/octet-stream", "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<String> shareableURL(@Parameter(in = ParameterIn.PATH, description = "The id of the file to retrieve", required=true, schema=@Schema()) @PathVariable("fileId") String fileId, @Parameter(in = ParameterIn.QUERY, description = "" ,schema=@Schema()) @Valid @RequestParam(value = "userName", required = false) String userName, @Parameter(in = ParameterIn.QUERY, description = "" ,schema=@Schema()) @Valid @RequestParam(value = "expires", required = false) String expires, @RequestAttribute("user") User user);


    @Operation(summary = "Info for a specific file, meta data only. Does not download the content.", description = "", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "files" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Expected response to a valid request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = File.class))),
        
        @ApiResponse(responseCode = "401", description = "Authentication information is missing or invalid"),
        
        @ApiResponse(responseCode = "200", description = "unexpected error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))) })
    @RequestMapping(value = "/files/{fileId}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<File> showFileById(@Parameter(in = ParameterIn.PATH, description = "The id of the file to retrieve", required=true, schema=@Schema()) @PathVariable("fileId") String fileId);


    @Operation(summary = "Upload a file", description = "", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "files" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "201", description = "Upload File response", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = Boolean.class))),
        
        @ApiResponse(responseCode = "401", description = "Authentication information is missing or invalid"),
        
        @ApiResponse(responseCode = "200", description = "unexpected error", content = @Content(mediaType = "multipart/form-data", schema = @Schema(implementation = Error.class))) })
    @RequestMapping(value = "/files/{fileId}/upload",
        produces = { "text/plain", "application/json" }, 
        consumes = { "multipart/form-data" }, 
        method = RequestMethod.POST)
    ResponseEntity<String> uploadFile(
        @Parameter(in = ParameterIn.PATH, description = "The id of the file to retrieve", required=true, schema=@Schema()) @PathVariable("fileId") String fileId,
        @Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestParam MultipartFile file,
        @RequestAttribute("user") User user
    ) throws IllegalStateException, IOException;

}

