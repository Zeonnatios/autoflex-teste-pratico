import React from 'react';
import PropTypes from 'prop-types';

function Message({ text }) {
  return (
    <div className="alert alert-primary" role="alert">
      {text}
    </div>
  );
}

Message.propTypes = {
  text: PropTypes.string.isRequired,
};

export default Message;
