package seedu.rc4hdb.logic.parser.commandparsers;

import static seedu.rc4hdb.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.rc4hdb.logic.commands.residentcommands.ModelCommandTestUtil.EMAIL_DESC_AMY;
import static seedu.rc4hdb.logic.commands.residentcommands.ModelCommandTestUtil.GENDER_DESC_AMY;
import static seedu.rc4hdb.logic.commands.residentcommands.ModelCommandTestUtil.HOUSE_DESC_AMY;
import static seedu.rc4hdb.logic.commands.residentcommands.ModelCommandTestUtil.MATRIC_NUMBER_DESC_AMY;
import static seedu.rc4hdb.logic.commands.residentcommands.ModelCommandTestUtil.NAME_DESC_AMY;
import static seedu.rc4hdb.logic.commands.residentcommands.ModelCommandTestUtil.PHONE_DESC_AMY;
import static seedu.rc4hdb.logic.commands.residentcommands.ModelCommandTestUtil.ROOM_DESC_AMY;
import static seedu.rc4hdb.logic.commands.residentcommands.ModelCommandTestUtil.TAG_DESC_FRIEND;
import static seedu.rc4hdb.logic.commands.residentcommands.ModelCommandTestUtil.VALID_ALL_SPECIFIER_DESC;
import static seedu.rc4hdb.logic.commands.residentcommands.ModelCommandTestUtil.VALID_EMAIL_AMY;
import static seedu.rc4hdb.logic.commands.residentcommands.ModelCommandTestUtil.VALID_GENDER_AMY;
import static seedu.rc4hdb.logic.commands.residentcommands.ModelCommandTestUtil.VALID_HOUSE_AMY;
import static seedu.rc4hdb.logic.commands.residentcommands.ModelCommandTestUtil.VALID_MATRIC_NUMBER_AMY;
import static seedu.rc4hdb.logic.commands.residentcommands.ModelCommandTestUtil.VALID_NAME_AMY;
import static seedu.rc4hdb.logic.commands.residentcommands.ModelCommandTestUtil.VALID_PHONE_AMY;
import static seedu.rc4hdb.logic.commands.residentcommands.ModelCommandTestUtil.VALID_ROOM_AMY;
import static seedu.rc4hdb.logic.commands.residentcommands.ModelCommandTestUtil.VALID_TAG_FRIEND;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.rc4hdb.logic.parser.commandparsers.CommandParserTestUtil.assertParseFailure;
import static seedu.rc4hdb.logic.parser.commandparsers.CommandParserTestUtil.assertParseSuccess;
import static seedu.rc4hdb.testutil.TypicalSpecifiers.ALL_SPECIFIER;

import org.junit.jupiter.api.Test;

import seedu.rc4hdb.logic.commands.residentcommands.FilterCommand;
import seedu.rc4hdb.model.resident.ResidentStringDescriptor;
import seedu.rc4hdb.testutil.ResidentStringDescriptorBuilder;

/**
 * Unit tests for {@link FilterCommandParser}.
 */
public class FilterCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE);

    private FilterCommandParser parser = new FilterCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no field specified
        assertParseFailure(parser, "filter /any", FilterCommand.MESSAGE_NOT_FILTERED);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "something else", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "/l strings", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        String userInput = VALID_ALL_SPECIFIER_DESC + PHONE_DESC_AMY + EMAIL_DESC_AMY + NAME_DESC_AMY + HOUSE_DESC_AMY
                + ROOM_DESC_AMY + GENDER_DESC_AMY + MATRIC_NUMBER_DESC_AMY + TAG_DESC_FRIEND;

        ResidentStringDescriptor descriptor = new ResidentStringDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withRoom(VALID_ROOM_AMY)
                .withGender(VALID_GENDER_AMY).withHouse(VALID_HOUSE_AMY).withTags(VALID_TAG_FRIEND)
                .withMatricNumber(VALID_MATRIC_NUMBER_AMY).build();
        FilterCommand expectedCommand = new FilterCommand(descriptor, ALL_SPECIFIER);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        String userInput = VALID_ALL_SPECIFIER_DESC + PHONE_DESC_AMY + EMAIL_DESC_AMY;

        ResidentStringDescriptor descriptor = new ResidentStringDescriptorBuilder().withPhone(VALID_PHONE_AMY)
                .withEmail(VALID_EMAIL_AMY).build();
        FilterCommand expectedCommand = new FilterCommand(descriptor, ALL_SPECIFIER);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

}
