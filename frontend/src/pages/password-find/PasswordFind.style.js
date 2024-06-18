import styled from 'styled-components';
import COLOR from '/src/constants/color';

export const Container = styled.div`
  max-width: 400px;
  margin: auto;
  padding: 20px;
  font-family: Arial, sans-serif;
`;

export const Header = styled.div`
  text-align: center;
  margin-bottom: 20px;
`;

export const Title = styled.h1`
  font-size: 1.5rem;
`;

export const Form = styled.form`
  display: flex;
  flex-direction: column;
`;

export const Label = styled.label`
  margin: 10px 0 10px 0;
  font-size: 1rem;
`;

export const NameWrapper = styled.div`
  margin-top: 2px;
`;

export const FormWrapper = styled.div`
  width: 320px;
  margin-top: 2px;
  border: 1px solid ${COLOR.HDSilver};
  border-radius: 4px;
`;

export const Input = styled.input`
  width: 93%;
  padding: 10px;
  margin: 5px 10px 0 0;
  border: 1px solid ${COLOR.HDSilver};
  border-radius: 4px;
`;

export const InputVerifyCode = styled.input`
  width: 100%;
  margin-left: 10px;
  border: none;
  border-radius: 4px;
`;

export const EmailForm = styled.form`
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
`;

export const CodeForm = styled.form`
  display: flex;
  flex-direction: row;
  justify-content: space-between;
`;

export const InputWithButton = styled.input`
  flex-grow: 1;
  margin-right: 10px;
  margin-left: 10px;
  border: none;
`;

export const Button = styled.button`
  margin: 5px 10px 5px 0;
  width: 50px;
  height: 30px;
  background-color: ${COLOR.HDDepartmentGreen};
  color: #fff;
  border: none;
  border-radius: 4px;
  cursor: pointer;
`;

export const Timer = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  margin-right: 10px;
  height: 40px;
  font-size: 1rem;
  margin-left: 5px;
`;

export const ConfirmButton = styled(Button)`
  margin-top: 20px;
  width: 100%;
`;
